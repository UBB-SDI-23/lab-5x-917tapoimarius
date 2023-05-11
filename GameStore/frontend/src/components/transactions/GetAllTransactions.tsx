import {useEffect, useState} from "react";
import {Transaction} from "../../models/Transaction";
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
import { Game } from "../../models/Game";

function GetAllTransactions() {
    const [transactions,
        setTransactions] = useState < Transaction[] > ([]);
    const [loading,
        setLoading] = useState(false);
    const [currentPage,
        setCurrentPage] = useState(1);
    const [count,
        setCount] = useState(1);
    const [pageVal, setPageVal] = useState(1);


    useEffect(() => {
        setLoading(true);
        fetch(`${BACKEND_API_URL}/transactions/count`)
            .then(res => res.json())
            .then(data => setCount(data));
        fetch(`${BACKEND_API_URL}/transactions/byPage/${currentPage - 1}`)
            .then(res => res.json())
            .then(data => {
                setTransactions(data);
                setLoading(false);
            })

    }, [currentPage]);

    return (
        <Container sx={{
            marginTop: "40px",
        }}>
            <Typography variant="h3" color="black" align="left">All Transactions</Typography>
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
                        <Button variant="outlined" component={Link} to={`/transactions/add`}>
                            + Add new transaction
                        </Button>
                    </ListItem>
                </List>
            )}
            {loading && <CircularProgress/>}
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
                                    Game
                                </TableCell>
                                <TableCell>
                                    Customer
                                </TableCell>
                                <TableCell>
                                    Format
                                </TableCell>
                                <TableCell>
                                    Quantity
                                </TableCell>
                                <TableCell>
                                    Checkout
                                </TableCell>
                                <TableCell>Operations</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {transactions.map((transaction : Transaction, index) => (
                                <TableRow key={index}>
                                    <TableCell>{index + 1}</TableCell>
                                    <TableCell>{transaction.gameEntity.name}</TableCell>
                                    <TableCell>{transaction.customerEntity.firstname} {transaction.customerEntity.lastname}</TableCell>
                                    <TableCell>{transaction.format}</TableCell>
                                    <TableCell>{transaction.quantity}</TableCell>
                                    <TableCell>{transaction.checkout}</TableCell>
                                    <TableCell>
                                        <IconButton
                                            component={Link}
                                            sx={{
                                            mr: 3
                                        }}
                                            to={`/transactions/delete/${transaction.id}`}>
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
                                            to={`/transactions/update/${transaction.id}`}>
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
                                value={pageVal}
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

export default GetAllTransactions;