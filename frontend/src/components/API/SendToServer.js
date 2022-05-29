import { Card, CardContent, Grid, Typography, Button } from "@mui/material";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";

const axios = require("axios");

const minioBucketGenerateClick = () => {
  axios.get("http://localhost:8080/makebk");
};

export const SendToServer = (props) => (
  <Card sx={{ height: "100%" }} {...props}>
    <CardContent>
      <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
        <Grid item>
          <Typography color="textSecondary" gutterBottom variant="overline">
            MinIO Bucket
          </Typography>
          <Typography color="textPrimary" variant="h5">
            Send To
          </Typography>
          <Typography color="textPrimary" variant="h5">
            Cloud
          </Typography>
        </Grid>
        <Grid item>
          <Button onClick={minioBucketGenerateClick}>
            <CloudUploadIcon
              sx={{
                // backgroundColor: "warning.main",
                height: 50,
                width: 50,
                color: "green",
              }}
            ></CloudUploadIcon>
          </Button>
        </Grid>
      </Grid>
    </CardContent>
  </Card>
);
