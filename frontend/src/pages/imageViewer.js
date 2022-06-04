import Head from "next/head";
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
  Dialog,
  AlertTitle,
} from "@mui/material";
import { DashboardLayout } from "../components/dashboard-layout";
import { Pipeline } from "../components/ImageViwer/Pipeline";
import TimelineIcon from "@mui/icons-material/Timeline";
import React from "react";

const axios = require("axios");

const ImageViewer = () => {
  const [isImageGenerated, setIsImageGenerated] = React.useState(false);
  const [isDetected, setIsDetected] = React.useState(false);

  const handleClick = () => {
    setIsDetected(!isDetected);
  };

  const ImageGenerated = () => {
    setIsImageGenerated(!isImageGenerated);
  };

  const minioPipelineClick = () => {
    handleClick();
    setTimeout(() => {
      axios.get("http://localhost:8080/pipeline").then((res) => {
        ImageGenerated();
      });
    }, 1000);
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
            {/* <Grid item lg={3} sm={4} xl={3} xs={4}> */}
            <Grid item xs={4}>
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
                            // backgroundColor: "error.main",
                            height: 56,
                            width: 56,
                          }}
                        ></TimelineIcon>
                      </Button>
                    </Grid>
                  </Grid>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={8} sm={8}>
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
              {isImageGenerated ? (
                <CardMedia
                  component="img"
                  height="100%"
                  image="/static/images/minioLogo.png"
                  alt="green iguana"
                />
              ) : (
                <Card>
                  <CircularProgress sx={{ m: "50%" }}></CircularProgress>
                </Card>
              )}
            </Grid>
          </Grid>
        </Container>
      </Box>
    </>
  );
};

ImageViewer.getLayout = (page) => <DashboardLayout>{page}</DashboardLayout>;

export default ImageViewer;
