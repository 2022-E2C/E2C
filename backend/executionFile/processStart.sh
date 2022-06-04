#!/bin/bash
source ~/anaconda3/etc/profile.d/conda.sh
conda activate tensorflow
python3 /home/mdcl/IdeaProjects/E2C/backend/executionFile/processData.py
wait
