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

const orders = [
  {
    id: uuid(),
    ref: "test1",
    amount: 30.5,
    customer: {
      name: "Sanghyun Park",
    },
    createdAt: 1555016400000,
    Authority: "Read/Write",
  },
  {
    id: uuid(),
    ref: "test2",
    amount: 25.1,
    customer: {
      name: "HongGi Oh",
    },
    createdAt: 1555016400000,
    Authority: "Read/Write",
  },
  {
    id: uuid(),
    ref: "test3",
    amount: 10.99,
    customer: {
      name: "KangPark Kim",
    },
    createdAt: 1554930000000,
    Authority: "Read/Only",
  },
];

export const LatestOrders = (props) => (
  <Card {...props}>
    <CardHeader title="Bucket List" />
    <PerfectScrollbar>
      <Box sx={{ minWidth: 800 }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Bucket name</TableCell>
              <TableCell>Object Number</TableCell>
              <TableCell sortDirection="desc">
                <Tooltip enterDelay={300} title="Sort">
                  <TableSortLabel active direction="desc">
                    created Date
                  </TableSortLabel>
                </Tooltip>
              </TableCell>
              <TableCell>Access Authority</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {orders.map((order) => (
              <TableRow hover key={order.id}>
                <TableCell>{order.ref}</TableCell>
                <TableCell>{order.customer.name}</TableCell>
                <TableCell>{format(order.createdAt, "dd/MM/yyyy")}</TableCell>
                <TableCell>
                  <SeverityPill
                    color={
                      (order.Authority === "Read/Write" && "success") ||
                      (order.Authority === "Read/Only" && "warning") ||
                      "error"
                    }
                  >
                    {order.Authority}
                  </SeverityPill>
                </TableCell>
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
