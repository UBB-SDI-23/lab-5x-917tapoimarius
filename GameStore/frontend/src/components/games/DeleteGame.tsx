import { Button, Card, CardActions, Container, IconButton } from "@mui/material";
import {Link, useNavigate, useParams} from "react-router-dom";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import { BACKEND_API_URL } from "../../constants";

export const DeleteGame = () => {
    const {id} = useParams();
    const navigate = useNavigate();

    const handleDelete = async (event: { preventDefault: () => void }) => {
        event.preventDefault();
        await axios.delete(`${BACKEND_API_URL}/games/${id}`);
        navigate("/games/");
    }

    return ( 
        <Container>
            <Card>
                <IconButton component={Link} to={`/games/`}>
                    <ArrowBackIcon/>
                </IconButton>
                Are you sure you want to delete the selected Game?
            <CardActions>
               <Button onClick={handleDelete}>Delete it!</Button>
               <Button onClick={() => navigate("/games/")}>Cancel</Button>
            </CardActions>
            </Card>
        </Container>
    )
}