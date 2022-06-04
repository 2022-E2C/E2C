import { Avatar, Box, Card, CardContent, Grid, Typography, Button } from "@mui/material";
import ArrowDownwardIcon from "@mui/icons-material/ArrowDownward";
import MoneyIcon from "@mui/icons-material/Money";
import RestartAltIcon from "@mui/icons-material/RestartAlt";
import React from "react";

const axios = require("axios");

const minioBootClick = () => {
  axios.get("http://localhost:8080/boot");
};

export const Boot = (props) => (
  <Card sx={{ height: "100%" }} {...props}>
    <CardContent>
      <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
        <Grid item>
          <Typography color="textSecondary" gutterBottom variant="overline">
            MiNIO SERVER RUN
          </Typography>
          <Typography color="textPrimary" variant="h5">
            Boot
          </Typography>
        </Grid>
        <Grid item>
          <Button onClick={minioBootClick}>
            <RestartAltIcon
              sx={{
                // backgroundColor: "error.main",
                height: 56,
                width: 56,
              }}
            ></RestartAltIcon>
          </Button>
        </Grid>
      </Grid>
      {/* <Box
        sx={{
          pt: 2,
          display: "flex",
          alignItems: "center",
        }}
      > */}
      {/* <ArrowDownwardIcon color="error" />
        <Typography
          color="error"
          sx={{
            mr: 1,
          }}
          variant="body2"
        >
          12%
        </Typography>
        <Typography color="textSecondary" variant="caption">
          Since last month
        </Typography> */}
      {/* </Box> */}
    </CardContent>
  </Card>
);
