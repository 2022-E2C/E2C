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
  Menu,
  MenuItem,
  Fade,
} from "@mui/material";
import ArrowRightIcon from "@mui/icons-material/ArrowRight";
import _ from "lodash";
import Moment from "moment";
import "moment/locale/ko";
import React, { useState } from "react";

export const MinioBuckets = (props) => {
  const [anchorEl, setanchorEl] = useState(null);
  const open = Boolean(anchorEl);

  const handleClick = (event) => {
    setanchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setanchorEl(null);
  };

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
                <TableCell>Object List</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {props.list.buckets.map((bucket) => (
                <TableRow hover key={bucket.name}>
                  <TableCell>{bucket.name}</TableCell>
                  <TableCell>{bucket.objectNumber}</TableCell>
                  <TableCell>{Moment(bucket.createdTime).format("YYYY-MM-DD HH:mm:ss")}</TableCell>
                  <TableCell>
                    <Button
                      id="fade-button"
                      aria-controls={open ? "fade-menu" : undefined}
                      aria-expanded={open ? "true" : undefined}
                      aria-haspopup="true"
                      onClick={handleClick}
                      variant="contained"
                      disableElevation
                    >
                      Object List
                    </Button>
                    <Menu
                      key={bucket.name}
                      id="fade-menu"
                      MenuListProps={{
                        "aria-labelledby": "fade-button",
                      }}
                      anchorEl={anchorEl}
                      open={open}
                      onClose={handleClose}
                    >
                      {bucket.objects.map((object) =>
                        object ? (
                          <MenuItem key={object.name} onClick={handleClose}>
                            {object.name}
                          </MenuItem>
                        ) : (
                          <div></div>
                        )
                      )}
                    </Menu>
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
};
