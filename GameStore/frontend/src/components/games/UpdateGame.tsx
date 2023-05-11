import { useNavigate, useParams } from "react-router-dom"
import { GameForm } from "./GameForm";
import { useEffect, useState } from "react";
import { Game } from "../../models/Game";
import { Developer } from "../../models/Developer";
import { BACKEND_API_URL } from "../../constants";
import axios from "axios";

export const UpdateGame = () => {
    const navigate = useNavigate();
    const {id} = useParams();

    useEffect(() => {
        fetch(`${BACKEND_API_URL}/games/${id}`)
            .then(res => res.json())
            .then(data => {
                setGame(data);
            })
    }, []);;

    const [game, setGame] = useState<Game>(new Game());

	const updateGame = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			console.log(game);
			await axios.put(`${BACKEND_API_URL}/games/${id}`, game);
			navigate("/games/");
		} catch (error) {
			console.log(error);
		}
	};

    return(
        <GameForm
            apiCallMethod={updateGame}
            game={game}
            setGame={setGame}
            btnMsg="Update game"
        />
    )
}