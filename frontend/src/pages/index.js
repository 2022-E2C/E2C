import React, { useEffect, useState } from "react";
import Head from "next/head";
import { Box, Container, Grid, Typography } from "@mui/material";
import { MinioBuckets } from "../components/dashboard/minio-buckets";
import { DiskUsage } from "../components/dashboard/DiskUsage";
import { DashboardLayout } from "../components/dashboard-layout";

const axios = require("axios");

const Dashboard = () => {
  const [buckets, setBuckets] = useState([]);
  // const [minioUsage, setMinioUsage] = React.useState(0);
  const [diskCapacity, setDiskCapacity] = useState(0);
  const [minioCapacity, setMinioCapacity] = useState(0);
  const [diskRate, setDiskRate] = useState(0);
  const [minioRate, setMinioRate] = useState(0);

  useEffect(() => {
    axios
      .get("http://localhost:8080/bucket-detail-page")
      .then((res) => {
        setBuckets(res.data.bucketList);
        console.log(res.data);

        // setMinioCapacity(props.usage.minioUsage / 1000000); //추후 GB로 계산
        // setDiskCapacity(256); // 256GB 예시
        // 보여주기 위한 데이터 수정 (disk - 100MB, minioSize - 2.2MB)
        const minioUsage = res.data.minioUsage / 100;
        const diskUsage = 100;
        setMinioCapacity(minioUsage);
        setDiskCapacity(diskUsage);
        setDiskRate(((diskUsage / (diskUsage + minioUsage)) * 100).toFixed(1));
        setMinioRate(((minioUsage / (diskUsage + minioUsage)) * 100).toFixed(1));
      })
      .catch((e) => {
        console.error(e);
      });
  }, []);

  return (
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
            <Grid item lg={4} md={6} xl={3} xs={12}>
              <DiskUsage
                sx={{ height: "100%" }}
                usage={{ diskCapacity, minioCapacity, diskRate, minioRate }}
              />
            </Grid>
            <Grid item lg={8} md={12} xl={9} xs={12}>
              <MinioBuckets list={{ buckets }} />
            </Grid>
          </Grid>
        </Container>
      </Box>
    </>
  );
};

Dashboard.getLayout = (page) => <DashboardLayout>{page}</DashboardLayout>;

export default Dashboard;
