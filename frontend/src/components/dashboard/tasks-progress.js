import {
  Avatar,
  Box,
  Card,
  CardContent,
  Grid,
  LinearProgress,
  Typography,
  Button,
} from "@mui/material";
import InsertChartIcon from "@mui/icons-material/InsertChartOutlined";
import SyncDisabledIcon from "@mui/icons-material/SyncDisabled";

const axios = require("axios");

const minioTerminateClick = () => {
  axios.get("http://localhost:8080/terminate");
};

export const TasksProgress = (props) => (
  <Card sx={{ height: "100%" }} {...props}>
    <CardContent>
      <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
        <Grid item>
          <Typography color="textSecondary" gutterBottom variant="overline">
            MinIO Server Down
          </Typography>
          <Typography color="textPrimary" variant="h5">
            Terminate
          </Typography>
        </Grid>
        <Grid item>
          <Button onClick={minioTerminateClick}>
            <SyncDisabledIcon
              sx={{
                // backgroundColor: "warning.main",
                height: 50,
                width: 45,
              }}
            ></SyncDisabledIcon>
          </Button>
        </Grid>
      </Grid>
      {/* <Box sx={{ pt: 3 }}>
        <LinearProgress value={75.5} variant="determinate" />
      </Box> */}
    </CardContent>
  </Card>
);
