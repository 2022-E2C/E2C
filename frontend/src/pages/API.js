import Head from "next/head";
import { Box, Container, Grid, Pagination } from "@mui/material";
import { products } from "../__mocks__/products";
import { ProductListToolbar } from "../components/product/product-list-toolbar";
import { ProductCard } from "../components/product/product-card";
import { DashboardLayout } from "../components/dashboard-layout";
import { Boot } from "../components/API/Booting";
import { TotalCustomers } from "../components/API/total-customers";
import { Terminate } from "../components/API/Terminate";
import { MakeBucket } from "../components/API/MakeBucket";
import { BucketUpload } from "../components/API/BucketUpload";
import { ImageProcessing } from "../components/API/ImageProcessing";
import { SendToServer } from "../components/API/SendToServer";

const API = () => (
  <>
    <Head>
      <title>API | Material Kit</title>
    </Head>
    <Box
      component="main"
      sx={{
        flexGrow: 1,
        py: 8,
      }}
    >
      <Container maxWidth={false}>
        <ProductListToolbar />
        <Grid container spacing={3}>
          <Grid item lg={3} sm={3} xl={6} xs={12}>
            <Boot />
          </Grid>
          {/* <Grid item xl={3} lg={3} sm={6} xs={12}>
            <MakeBucket />
          </Grid> */}
          <Grid item xl={3} lg={3} sm={6} xs={12}>
            <BucketUpload />
          </Grid>
          <Grid item xl={3} lg={3} sm={6} xs={12}>
            <SendToServer />
          </Grid>
          <Grid item xl={3} lg={3} sm={6} xs={12}>
            <ImageProcessing />
          </Grid>
          <Grid item xl={3} lg={3} sm={6} xs={12}>
            <Terminate />
          </Grid>
        </Grid>
        {/* <Box
          sx={{
            display: "flex",
            justifyContent: "center",
            pt: 3,
          }}
        > */}
        {/* <Pagination color="primary" count={3} size="small" />
        </Box> */}
      </Container>
    </Box>
  </>
);

API.getLayout = (page) => <DashboardLayout>{page}</DashboardLayout>;

export default API;
