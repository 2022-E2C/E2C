import { Card, CardContent, Grid, Typography, Button } from "@mui/material";
import InsertChartIcon from "@mui/icons-material/InsertChartOutlined";
import SaveAltIcon from "@mui/icons-material/SaveAlt";

const axios = require("axios");

const minioBucketUploadClick = () => {
  axios.get("http://localhost:8080/bkupload");
};

export const BucketUpload = (props) => (
  <Card sx={{ height: "100%" }} {...props}>
    <CardContent>
      <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
        <Grid item>
          <Typography color="textSecondary" gutterBottom variant="overline">
            MinIO BUCKET
          </Typography>
          <Typography color="textPrimary" variant="h5">
            Upload Data
          </Typography>
        </Grid>
        <Grid item>
          <Button onClick={minioBucketUploadClick}>
            <SaveAltIcon
              sx={{
                // backgroundColor: "warning.main",
                height: 50,
                width: 45,
                color: "blue",
              }}
            ></SaveAltIcon>
          </Button>
        </Grid>
      </Grid>
      {/* <Box sx={{ pt: 3 }}>
        <LinearProgress value={75.5} variant="determinate" />
      </Box> */}
    </CardContent>
  </Card>
);
