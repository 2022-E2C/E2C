import Head from "next/head";
import { Box, Container, Grid, Pagination } from "@mui/material";
import { products } from "../__mocks__/products";
import { ProductListToolbar } from "../components/product/product-list-toolbar";
import { ProductCard } from "../components/product/product-card";
import { DashboardLayout } from "../components/dashboard-layout";
import { Budget } from "../components/dashboard/budget";
import { TotalCustomers } from "../components/dashboard/total-customers";
import { TasksProgress } from "../components/dashboard/tasks-progress";

const Products = () => (
  <>
    <Head>
      <title>Products | Material Kit</title>
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
          <Grid item lg={3} sm={6} xl={3} xs={12}>
            <Budget />
          </Grid>
          <Grid item xl={3} lg={3} sm={6} xs={12}>
            <TotalCustomers />
          </Grid>
          <Grid item xl={3} lg={3} sm={6} xs={12}>
            <TasksProgress />
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

Products.getLayout = (page) => <DashboardLayout>{page}</DashboardLayout>;

export default Products;
