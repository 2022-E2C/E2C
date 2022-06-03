import { format } from "date-fns";
import { v4 as uuid } from "uuid";
import PerfectScrollbar from "react-perfect-scrollbar";
import {
  Box,
  Button,
  Card,
  CardHeader,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  TableSortLabel,
  Tooltip,
} from "@mui/material";
import ArrowRightIcon from "@mui/icons-material/ArrowRight";
import { SeverityPill } from "../severity-pill";
import { render } from "nprogress";
import { TextSnippet } from "@mui/icons-material";
import React, { useState, useEffect, Component } from "react";
import _ from "lodash";
import { is } from "date-fns/locale";

const axios = require("axios");

export const MinioBuckets = (props) => {
  const [Buckets, setBuckets] = React.useState([]);
  function minioBucktsInfo() {
    setTimeout(() => {
      axios
        .get("http://localhost:8080/bucket-detail-page")
        .then((res) => {
          setBuckets(res.data);
        })
        .catch((e) => {
          console.error(e);
        });
    }, 5000);
  }

  return (
    <Card {...props} render={minioBucktsInfo()}>
      <CardHeader title="Bucket List" />
      <PerfectScrollbar>
        <Box sx={{ minWidth: 800 }}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Bucket name</TableCell>
                <TableCell>Object Numbers</TableCell>
                <TableCell sortDirection="desc">
                  <Tooltip enterDelay={300} title="Sort">
                    <TableSortLabel active direction="desc">
                      created Dates
                    </TableSortLabel>
                  </Tooltip>
                </TableCell>
                <TableCell>Access Authority</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {/* {orders.map((order) => (
//               <TableRow hover key={order.id}>
//                 <TableCell>{order.ref}</TableCell>
//                 <TableCell>{order.customer.name}</TableCell>
//                 <TableCell>{format(order.createdAt, "dd/MM/yyyy")}</TableCell>
//                 <TableCell>
//                   <Button onClick={minioBucktsInfo}></Button>
//                   <SeverityPill
//                     color={
//                       (order.Authority === "Read/Write" && "success") ||
//                       (order.Authority === "Read/Only" && "warning") ||
//                       "error"
//                     }
//                   >
//                     {order.Authority}
//                   </SeverityPill>
//                 </TableCell>
//               </TableRow>
//             ))} */}
              {Buckets.map((bucket) => (
                <TableRow hover key={uuid()}>
                  <TableCell>{bucket.name}</TableCell>
                  <TableCell>{bucket.objectNumber}</TableCell>
                  <TableCell>{bucket.createdTime}</TableCell>
                  <TableCell>{bucket.objects.map((object) => object.name)}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </Box>
      </PerfectScrollbar>
      <Box
        sx={{
          display: "flex",
          justifyContent: "flex-end",
          p: 2,
        }}
      >
        <Button
          color="primary"
          endIcon={<ArrowRightIcon fontSize="small" />}
          size="small"
          variant="text"
        >
          View all
        </Button>
      </Box>
    </Card>
  );
};
