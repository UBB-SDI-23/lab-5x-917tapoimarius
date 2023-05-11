import {useEffect, useState} from "react";
import {Customer} from '../../models/Customer';
import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    IconButton,
    Tooltip,
    Container,
    Button,
    Typography,
    List,
    ListItem,
    TextField,
    CircularProgress,
    TableSortLabel
} from "@mui/material";
import Pagination from '../pagination/Pagination'
import AddIcon from "@mui/icons-material/Add";
import DeleteForeverIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit"
import {Link} from "react-router-dom";
import {BACKEND_API_URL} from "../../constants";
import {inherits} from "util";

function GetAllCustomer() {
    const [customers,
        setCustomers] = useState < Customer[] > ([]);
    const [loading,
        setLoading] = useState(false);
    const [currentPage,
        setCurrentPage] = useState(1);
    const [count,
        setCount] = useState(1);
    const [pageVal, setPageVal] = useState(1);


    useEffect(() => {
        setLoading(true);
        fetch(`${BACKEND_API_URL}/customers/count`)
            .then(res => res.json())
            .then(data => setCount(data));
        fetch(`${BACKEND_API_URL}/customers/byPage/${currentPage - 1}`)
            .then(res => res.json())
            .then(data => {
                setCustomers(data);
                setLoading(false);
            })

    }, [currentPage]);

    return (
        <Container sx={{
            marginTop: "40px",
        }}>
            <Typography variant="h3" color="black" align="left">All Customers</Typography>
            {(
                <List
                    sx={{
                    display: "flex",
                    flexDirection: "row",
                    padding: "1px"
                }}>
                    <ListItem sx={{
                        width: "auto"
                    }}>
                        <Button variant="outlined" component={Link} to={`/customers/add`}>
                            + Add new customer
                        </Button>
                        <Button variant="outlined" component={Link} to={`/customers/customersSpent`}>
                            Get customers sorted by the money they spent on games.
                        </Button>
                    </ListItem>
                </List>
            )}
            {!loading && (
                <TableContainer component={Paper} >
                      <List
                        sx={{
                        display: "flex",
                        flexDirection: "row",
                        padding: "1px"
                    }}>
                        <ListItem>
                            <Pagination
                                onPageChange={(page : number) => setCurrentPage(page)}
                                totalCount={count}
                                currentPage={currentPage}
                                pageSize={10}
                                className="pagination-bar"/>
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="page"
                                label="Page:"
                                type="number"
                                sx={{
                                mb: 2
                            }}
                                onChange={(event) => {
                                    setPageVal(Number(event.target.value));
                                }}
                                onKeyDown={(event) => {
                                if (event.key === "Enter") 
                                    setCurrentPage(pageVal);
                                }}></TextField>
                        </ListItem>
                    </List>
                    <Table>
                        <TableHead
                            sx={{
                            backgroundColor: '#F5F5F5'
                        }}>
                            <TableRow>
                                <TableCell>#</TableCell>
                                <TableCell>
                                    First Name
                                </TableCell>
                                <TableCell>
                                    Last Name
                                </TableCell>
                                <TableCell>
                                    Email
                                </TableCell>
                                <TableCell>
                                    Address
                                </TableCell>
                                <TableCell>
                                    Phone number
                                </TableCell>
                                <TableCell>
                                    Number of purchased games
                                </TableCell>
                                <TableCell>Operations</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {customers.map((customer : Customer, index) => (
                                <TableRow key={index}>
                                    <TableCell>{index + 1}</TableCell>
                                    <TableCell>{customer.firstname}</TableCell>
                                    <TableCell>{customer.lastname}</TableCell>
                                    <TableCell>{customer.email}</TableCell>
                                    <TableCell>{customer.address}</TableCell>
                                    <TableCell>{customer.phoneNumber}</TableCell>
                                    <TableCell>{customer.totalNumberOfBoughtGames}</TableCell>
                                    <TableCell>
                                        <IconButton
                                            component={Link}
                                            sx={{
                                            mr: 3
                                        }}
                                            to={`/customers/delete/${customer.id}`}>
                                            <Tooltip title="Delete" arrow>
                                                <DeleteForeverIcon
                                                    sx={{
                                                    color: "red"
                                                }}/>
                                            </Tooltip>
                                        </IconButton>
                                        <IconButton
                                            component={Link}
                                            sx={{
                                            mr: 3
                                        }}
                                            to={`/customers/update/${customer.id}`}>
                                            <Tooltip title="Update" arrow>
                                                <EditIcon/>
                                            </Tooltip>
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                    <List
                        sx={{
                        display: "flex",
                        flexDirection: "row",
                        padding: "1px"
                    }}>
                        <ListItem>
                            <Pagination
                                onPageChange={(page : number) => setCurrentPage(page)}
                                totalCount={count}
                                currentPage={currentPage}
                                pageSize={10}
                                className="pagination-bar"/>
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="page"
                                label="Page:"
                                type="number"
                                sx={{
                                mb: 2
                            }}
                                onChange={(event) => {
                                    setPageVal(Number(event.target.value));
                                }}
                                onKeyDown={(event) => {
                                if (event.key === "Enter") 
                                    setCurrentPage(pageVal);
                                }}></TextField>
                        </ListItem>
                    </List>
                </TableContainer>
            )}
        </Container>
    );
}

export default GetAllCustomer;