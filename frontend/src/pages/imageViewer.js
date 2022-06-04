import Head from "next/head";
import React from "react";
import {
  Box,
  Container,
  Grid,
  Card,
  CardContent,
  Typography,
  Button,
  CardMedia,
  CircularProgress,
  Alert,
  AlertTitle,
  Dialog,
  Input,
  Divider,
} from "@mui/material";
import { DashboardLayout } from "../components/dashboard-layout";
import TimelineIcon from "@mui/icons-material/Timeline";
import UploadFileIcon from "@mui/icons-material/UploadFile";

const axios = require("axios");

const ImageViewer = () => {
  const [isImageGenerated, setIsImageGenerated] = React.useState(false);
  const [isImageUpload, setIsImageUpload] = React.useState(false);
  const [isDetected, setIsDetected] = React.useState(false);
  const [preview, setPreview] = React.useState(undefined);

  const handleClick = () => {
    setIsDetected(!isDetected);
  };

  const minioPipelineClick = () => {
    handleClick();
    setIsImageUpload(false);
    axios.get("http://localhost:8080/pipeline").then((res) => {
      setTimeout(() => {
        axios.get("http://localhost:8080/images/Processed_Test_image.jpg").then((res) => {
          setIsImageGenerated(true);
          setIsDetected(false);
        });
      }, 2000);
    });
  };

  const uploadFile = (e) => {
    const image = e.target.files[0];
    const url = URL.createObjectURL(image);
    setPreview(url);
    setIsImageUpload(true);
    setIsImageGenerated(false);
  };

  return (
    <>
      <Head>
        <title>ImageViewer | Material Kit</title>
      </Head>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          py: 8,
        }}
      >
        <Container maxWidth={false}>
          <Typography sx={{ m: 2 }} variant="h4">
            Image View
          </Typography>
          <Grid container spacing={3}>
            <Grid item lg={3} md={3} sm={4} xs={12}>
              <Card sx={{ height: "50" }}>
                <CardContent>
                  <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
                    <Grid item>
                      <Typography color="textSecondary" gutterBottom variant="overline">
                        MiNIO PIPELINE
                      </Typography>
                      <Typography color="textPrimary" variant="h5">
                        Pipeline
                      </Typography>
                    </Grid>
                    <Grid item>
                      <Button onClick={minioPipelineClick}>
                        <TimelineIcon
                          sx={{
                            height: 56,
                            width: 56,
                          }}
                        ></TimelineIcon>
                      </Button>
                    </Grid>
                  </Grid>
                </CardContent>
                <Divider variant="middle" />
                <Box sx={{ p: 2 }}>
                  <label htmlFor="contained-button-file">
                    <Button variant="contained" component="span" startIcon={<UploadFileIcon />}>
                      Upload Image
                      <Input
                        type="file"
                        accept=".png"
                        id="contained-button-file"
                        onChange={uploadFile}
                        sx={{ display: "none" }}
                      />
                    </Button>
                  </label>
                </Box>
              </Card>
            </Grid>
            <Grid item lg={9} md={9} sm={8} xs={12}>
              <Dialog open={isDetected} onClose={handleClick}>
                <Alert
                  severity="warning"
                  color="success"
                  role="button"
                  icon={<CircularProgress></CircularProgress>}
                  onClose={() => {}}
                  closeText="Done!"
                  sx={{
                    "& .MuiAlert-icon": {
                      color: "blue",
                    },
                  }}
                >
                  <AlertTitle>waiting</AlertTitle>
                  Detection is running...
                </Alert>
              </Dialog>
              <Card>
                {isImageUpload ? (
                  <CardMedia
                    component="img"
                    height="100%"
                    src={preview ? preview : ""}
                    alt="green iguana"
                  />
                ) : isImageGenerated ? (
                  <CardMedia
                    component="img"
                    height="100%"
                    src="http://localhost:8080/images/Processed_Test_image.jpg"
                    alt="green iguana"
                  />
                ) : (
                  <CircularProgress sx={{ m: "50%" }}></CircularProgress>
                )}
              </Card>
            </Grid>
          </Grid>
        </Container>
      </Box>
    </>
  );
};

ImageViewer.getLayout = (page) => <DashboardLayout>{page}</DashboardLayout>;

export default ImageViewer;
