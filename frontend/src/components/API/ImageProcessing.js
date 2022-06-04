import { Card, CardContent, Grid, Typography, Button } from "@mui/material";
import BurstModeIcon from "@mui/icons-material/BurstMode";

const axios = require("axios");

const minioImageProcessingClick = () => {
  axios.get("http://localhost:8080/process-image");
};

export const ImageProcessing = (props) => (
  <Card sx={{ height: "100%" }} {...props}>
    <CardContent>
      <Grid container sx={{ justifyContent: "space-between" }}>
        <Grid item>
          <Typography color="textSecondary" gutterBottom variant="overline">
            Minio data processing
          </Typography>
          <Typography color="textPrimary" variant="h5">
            Process Data
          </Typography>
        </Grid>
        <Grid item>
          <Button onClick={minioImageProcessingClick}>
            <BurstModeIcon
              sx={{
                // backgroundColor: "warning.main",
                height: 50,
                width: 40,
                color: "blue",
              }}
            ></BurstModeIcon>
          </Button>
        </Grid>
      </Grid>
    </CardContent>
  </Card>
);
