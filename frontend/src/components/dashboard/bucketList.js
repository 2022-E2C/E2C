import { useState } from "react";

const axios = require("axios");
const Buckets = { list: [] };

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
const build = () => {
  minioBucktsInfo();
  return Buckets;
};

export default build;
// export const store = {
// 	state: {},
// 	setState (value) {
// 		this.state = value
// 		this.setters.forEach(setter => setter(this.state))
// 	},
// 	setters: []
// }

// export const BucketCell = () => (
//   <TableBody render={minioBucktsInfo()}>
//     {/* {orders.map((order) => (
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
//     {buckets.bucketList.map((bucket) => (
//       <TableRow>
//         <TableCell>{bucket.name}</TableCell>
//         <TableCell>{bucket.objectNumber}</TableCell>
//         <TableCell>{bucket.createdTime}</TableCell>
//         <TableCell>
//           {/* {bucket.objects.map((object) => (
//             <TableCell>{object.name} </TableCell>
//           ))} */}
//           {/* <SeverityPill
//                     color={
//                       (order.Authority === "Read/Write" && "success") ||
//                       (order.Authority === "Read/Only" && "warning") ||
//                       "error"
//                     }
//                   >
//                     {order.Authority}
//                   </SeverityPill> */}
//         </TableCell>
//       </TableRow>
//     ))}
//   </TableBody>
// );
