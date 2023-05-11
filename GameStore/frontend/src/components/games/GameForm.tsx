import { Button, Card, CardActions, CardContent, IconButton, InputLabel, Select, TextField, MenuItem, Autocomplete} from "@mui/material";
import { Container } from "@mui/system";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { Game } from "../../models/Game";
import { Developer } from "../../models/Developer";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import {debounce} from "lodash";

export const GameForm = (
    { apiCallMethod, game, setGame, btnMsg} : 
    { apiCallMethod: any, game: Game, setGame: any, btnMsg: any}) =>{

	const [developers, setDevelopers] = useState<Developer[]>([]);

    const fetchSuggestions = async(query: string) => {
		try{
			const response = await axios.get<Developer[]>(
				`${BACKEND_API_URL}/developers/autocomplete?query=${query}`
			);
			const data = await response.data;
			setDevelopers(data);
		}catch(error){
			console.error("Error fetching suggestions:", error);
		}
	}
    const debouncedFetchSuggestions = useCallback(debounce(fetchSuggestions, 500), []);

	useEffect(() => {
		return () => {
			debouncedFetchSuggestions.cancel();
		};
	},[debouncedFetchSuggestions]);

	const handleInputChange = (event: any, value: any, reason: any) => {
		console.log("input", value, reason);

		if (reason === "input") {
			debouncedFetchSuggestions(value);
		}
	};

    return (
		<Container>
			<Card>
				<CardContent>
					<IconButton component={Link} to={`/games/`} sx={{float: "left"}}>
						<ArrowBackIcon/>
					</IconButton>
					<IconButton component={Link} sx={{ mr: 3 }} to={`/games/`}>
					</IconButton>{" "}
					<form onSubmit={apiCallMethod}>
						<TextField
							id="name"
							label="Name"
							variant="outlined"
                            value={game.name}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setGame({ ...game, name: event.target.value })}
						/>
						<TextField
							id="genre"
							label="Genre"
							variant="outlined"
                            value={game.genre}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setGame({ ...game, genre: event.target.value })}
						/>
						<TextField
							id="modes"
							label="Modes"
							variant="outlined"
                            value={game.modes}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setGame({ ...game, modes: event.target.value })}
						/>
						<TextField
							id="yearOfRelease"
							label="Year of Release"
							variant="outlined"
                            value={game.yearOfRelease}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setGame({ ...game, yearOfRelease: Number(event.target.value) })}
						/>
                        <TextField
							id="price"
							label="Price"
							variant="outlined"
                            value={game.price}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setGame({ ...game, price: Number(event.target.value) })}
						/>
						<TextField
							id="description"
							label="Description"
							variant="outlined"
                            value={game.description}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setGame({ ...game, description: event.target.value })}
						/>
                        <InputLabel sx={{float: "left"}}>
								Developer:
						</InputLabel>
                        <Autocomplete
							id="developerEntity"
                            options={developers}
							value={game.developerEntity}
							getOptionLabel={(option) => `${option.name} ${option.hq}`}
							renderInput={(params) => <TextField {...params} label="name, hq" variant="outlined" />}
							filterOptions={(x) => x}
							onInputChange={handleInputChange}
							onChange={(event, value) => {
								if (value) {
									setGame({ ...game, developerEntity: value as Developer});
								}
							}}
                            />
                        <Button type="submit" sx={{float: "left"}}>{btnMsg}</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
}