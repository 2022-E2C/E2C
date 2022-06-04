import Head from "next/head";
import { Box, Container, Grid, Typography } from "@mui/material";
import { MinioBuckets } from "../components/dashboard/minio-buckets";
import { LatestProducts } from "../components/dashboard/latest-products";
import { Sales } from "../components/dashboard/sales";
import { TotalCustomers } from "../components/API/total-customers";
import { TotalProfit } from "../components/dashboard/total-profit";
import { TrafficByDevice } from "../components/dashboard/traffic-by-device";
import { DashboardLayout } from "../components/dashboard-layout";
import { ProductListToolbar } from "../components/product/product-list-toolbar";
import { Buckets } from "../components/dashboard/bucketList";

const axios = require("axios");

function minioBucktsInfo() {
  axios
    .get("http://localhost:8080/bucket-detail-page")
    .then((res) => {
      // this.setBuckets({
      //   bucketList: res.data,
      // });
      // this.setState({
      //   bucketList: res.data,
      // });
      Buckets.list = res.data;
      console.log(Buckets.list);
    })
    .catch((e) => {
      console.error(e);
    });
}

const Dashboard = () => (
  <>
    <Head>
      <title>Dashboard | Material Kit</title>
    </Head>
    <Box
      component="main"
      sx={{
        flexGrow: 1,
        py: 2,
      }}
    >
      <Container maxWidth={false}>
        <Typography sx={{ m: 2 }} variant="h4">
          Disk
        </Typography>
        <Grid container spacing={3}>
          {/* <Grid item xl={3} lg={3} sm={6} xs={12}>
            <TotalCustomers />
          </Grid>
          <Grid item xl={3} lg={3} sm={6} xs={12}>
            <TotalProfit sx={{ height: "100%" }} />
          </Grid>
          <Grid item lg={8} md={12} xl={9} xs={12}>
            <Sales />
          </Grid> */}
          {/* <Grid item lg={4} md={6} xl={3} xs={12}>
            <LatestProducts sx={{ height: "100%" }} />
          </Grid> */}
          <Grid item lg={4} md={6} xl={3} xs={12}>
            <TrafficByDevice sx={{ height: "100%" }} />
          </Grid>
          <Grid item lg={8} md={12} xl={9} xs={12}>
            <MinioBuckets />
          </Grid>
        </Grid>
      </Container>
    </Box>
  </>
);

Dashboard.getLayout = (page) => <DashboardLayout>{page}</DashboardLayout>;

export default Dashboard;
