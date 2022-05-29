import { Card, CardContent, Grid, Typography, Button } from "@mui/material";
import AddToDriveIcon from "@mui/icons-material/AddToDrive";

const axios = require("axios");

const minioBucketGenerateClick = () => {
  axios.get("http://localhost:8080/makebk");
};

export const MakeBucket = (props) => (
  <Card sx={{ height: "100%" }} {...props}>
    <CardContent>
      <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
        <Grid item>
          <Typography color="textSecondary" gutterBottom variant="overline">
            MinIO Bucket
          </Typography>
          <Typography color="textPrimary" variant="h5">
            Bucket
          </Typography>
          <Typography color="textPrimary" variant="h5">
            Generate
          </Typography>
        </Grid>
        <Grid item>
          <Button onClick={minioBucketGenerateClick}>
            <AddToDriveIcon
              sx={{
                // backgroundColor: "warning.main",
                height: 50,
                width: 50,
                color: "green",
              }}
            ></AddToDriveIcon>
          </Button>
        </Grid>
      </Grid>
    </CardContent>
  </Card>
);
