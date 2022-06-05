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
import _ from "lodash";

export const MinioBuckets = (props) => {
  return (
    <Card {...props}>
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
              {props.list.buckets.map((bucket) => (
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
