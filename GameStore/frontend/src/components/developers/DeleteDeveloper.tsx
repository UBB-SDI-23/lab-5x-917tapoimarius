import { Button, Card, CardActions, Container, IconButton } from "@mui/material";
import {Link, useNavigate, useParams} from "react-router-dom";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import { BACKEND_API_URL } from "../../constants";

export const DeleteDeveloper = () => {
    const {id} = useParams();
    const navigate = useNavigate();

    const handleDelete = async (event: { preventDefault: () => void }) => {
        event.preventDefault();
        await axios.delete(`${BACKEND_API_URL}/developers/${id}`);
        navigate("/developers/");
    }

    return ( 
        <Container>
            <Card>
                <IconButton component={Link} to={`/developers/`}>
                    <ArrowBackIcon/>
                </IconButton>
                Are you sure you want to delete the selected developer?
            <CardActions>
               <Button onClick={handleDelete}>Delete it!</Button>
               <Button onClick={() => navigate("/developers/")}>Cancel</Button>
            </CardActions>
            </Card>
        </Container>
    )
}