import { useEffect, useState } from "react";
import { BACKEND_API_URL } from "../../constants";
import { Game } from "../../models/Game";
import { Developer } from "../../models/Developer";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { GameForm } from "./GameForm";

export const AddGame = () => {
	const navigate = useNavigate();

	const [game, setGame] = useState<Game>({
        name: "",
        genre: "",
        modes:  "",
        yearOfRelease: 0,
        price: 0,
        developerEntity: new Developer()
	});

	

	const addGame = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			console.log(game);
			console.log(game.developerEntity);
			await axios.post(`${BACKEND_API_URL}/games/`, game);
			navigate("/games/");
		} catch (error) {
			console.log(error);
		}
	};

	return(
		<GameForm
			apiCallMethod={addGame}
			game={game}
			setGame={setGame}
			btnMsg="Add game"
		/>
	)
};