#!/usr/bin/env python
# coding: utf-8

# In[62]:


import cv2

from PIL import Image
import tensorflow as tf
import numpy as np

#model_path = '/tmp/mobilenet_v1_1.0_224_quant.tflite'
model_path = '/tmp/model.tflite'

# Load the labels into a list
#classes = ['???'] * model.model_spec.config.num_classes
#label_map = model.model_spec.config.label_map
#for label_id, label_name in label_map.as_dict().items():
#  classes[label_id-1] = label_name
classes = ['Baked Goods', 'Salad', 'Cheese', 'Seafood', 'Tomato']

# Define a list of colors for visualization
#COLORS = np.random.randint(0, 255, size=(len(classes), 3), dtype=np.uint8)

#print(COLORS)

arr = [[0, 0, 0],
          [0, 255, 0],
          [255, 255, 0],
          [200, 200, 0],
          [255, 0, 0],
        ]
COLORS = np.array(arr, dtype=np.uint8)
#print()
#print(COLORS)

def preprocess_image(image_path, input_size):
  """Preprocess the input image to feed to the TFLite model"""
  img = tf.io.read_file(image_path)
  img = tf.io.decode_image(img, channels=3)
  img = tf.image.convert_image_dtype(img, tf.uint8)
  original_image = img
  resized_img = tf.image.resize(img, input_size)
  resized_img = resized_img[tf.newaxis, :]
  resized_img = tf.cast(resized_img, dtype=tf.uint8)
  return resized_img, original_image


def detect_objects(interpreter, image, threshold):
  """Returns a list of detection results, each a dictionary of object info."""

  signature_fn = interpreter.get_signature_runner()

  # Feed the input image to the model
  output = signature_fn(images=image)

  # Get all outputs from the model
  count = int(np.squeeze(output['output_0']))
  scores = np.squeeze(output['output_1'])
  classes = np.squeeze(output['output_2'])
  boxes = np.squeeze(output['output_3'])

  results = []
  for i in range(count):
    if scores[i] >= threshold:
      result = {
        'bounding_box': boxes[i],
        'class_id': classes[i],
        'score': scores[i]
      }
      results.append(result)
  return results


def run_odt_and_draw_results(image_path, interpreter, threshold=0.5):
  """Run object detection on the input image and draw the detection results"""
  # Load the input shape required by the model
  _, input_height, input_width, _ = interpreter.get_input_details()[0]['shape']

  # Load the input image and preprocess it
  preprocessed_image, original_image = preprocess_image(
      image_path,
      (input_height, input_width)
    )

  # Run object detection on the input image
  results = detect_objects(interpreter, preprocessed_image, threshold=threshold)

  # Plot the detection results on the input image
  original_image_np = original_image.numpy().astype(np.uint8)
  for obj in results:
    # Convert the object bounding box from relative coordinates to absolute
    # coordinates based on the original image resolution
    ymin, xmin, ymax, xmax = obj['bounding_box']
    xmin = int(xmin * original_image_np.shape[1])
    xmax = int(xmax * original_image_np.shape[1])
    ymin = int(ymin * original_image_np.shape[0])
    ymax = int(ymax * original_image_np.shape[0])

    # Find the class index of the current object
    class_id = int(obj['class_id'])

    # Draw the bounding box and label on the image
    color = [int(c) for c in COLORS[class_id]]
    cv2.rectangle(original_image_np, (xmin, ymin), (xmax, ymax), color, 2)
    # Make adjustments to make the label visible for all objects
    y = ymin - 15 if ymin - 15 > 15 else ymin + 15
    label = "{}: {:.0f}%".format(classes[class_id], obj['score'] * 100)
    cv2.putText(original_image_np, label, (xmin, y),
        cv2.FONT_HERSHEY_SIMPLEX, 0.5, color, 2)

  # Return the final image
  original_uint8 = original_image_np.astype(np.uint8)
  return original_uint8


# In[63]:


# INPUT_IMAGE_URL = "https://storage.googleapis.com/cloud-ml-data/img/openimage/3/2520/3916261642_0a504acd60_o.jpg" #@param {type:"string"}
DETECTION_THRESHOLD = 0.1 #@param {type:"number"}

#TEMP_FILE = '/tmp/image.png'
# TEMP_FILE = './backend/executionFile/data/Test_image.jpg'
# or
TEMP_FILE = '/home/mdcl/IdeaProjects/E2C/backend/executionFile/data/Test_image.jpg'

#!wget -q -O $TEMP_FILE $INPUT_IMAGE_URL
#im = Image.open(TEMP_FILE)
#im.thumbnail((512, 512), Image.ANTIALIAS)
#im.save(TEMP_FILE, 'PNG')

# Load the TFLite model
interpreter = tf.lite.Interpreter(model_path=model_path)
interpreter.allocate_tensors()

# Run inference and draw detection result on the local copy of the original file
detection_result_image = run_odt_and_draw_results(
    TEMP_FILE,
    interpreter,
    threshold=DETECTION_THRESHOLD
)

# Show the detection result
result_im = Image.fromarray(detection_result_image)
# result_im.save("/tmp/result.jpeg")
# result_im.save("./backend/executionFile/data/Processed_Test_image.jpg")
# or
result_im.save("/home/mdcl/IdeaProjects/E2C/backend/executionFile/data/Processed_Test_image.jpg")
print("Ho!")