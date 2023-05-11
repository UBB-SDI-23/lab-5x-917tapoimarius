import { Button, Card, CardActions, Container, IconButton } from "@mui/material";
import {Link, useNavigate, useParams} from "react-router-dom";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import { BACKEND_API_URL } from "../../constants";

export const DeleteCustomer = () => {
    const {id} = useParams();
    const navigate = useNavigate();

    const handleDelete = async (event: { preventDefault: () => void }) => {
        event.preventDefault();
        await axios.delete(`${BACKEND_API_URL}/customers/${id}`);
        navigate("/customers");
    }

    return ( 
        <Container>
            <Card>
                <IconButton component={Link} to={`/customers`}>
                    <ArrowBackIcon/>
                </IconButton>
                Are you sure you want to delete the selected customer?
            <CardActions>
               <Button onClick={handleDelete}>Delete it</Button>
               <Button onClick={() => navigate("/customers")}>Cancel</Button>
            </CardActions>
            </Card>
        </Container>
    )
}