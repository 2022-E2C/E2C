import { Card, CardContent, Grid, Typography, Button, CardMedia } from "@mui/material";
import TimelineIcon from "@mui/icons-material/Timeline";
import { React } from "react";

const axios = require("axios");

export const Pipeline = (props) => {
  return (
    <Card sx={{ height: "100%" }} {...props}>
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
  );
};
