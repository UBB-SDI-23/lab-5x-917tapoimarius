import { Button, Card, CardActions, CardContent, IconButton, InputLabel, Select, TextField, MenuItem, Autocomplete} from "@mui/material";
import { Container } from "@mui/system";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { Game } from "../../models/Game";
import { Customer } from "../../models/Customer";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import axios from "axios";
import {debounce} from "lodash";
import { Transaction } from "../../models/Transaction";

export const TransactionForm = (
    { apiCallMethod, transaction, setTransaction, btnMsg} : 
    { apiCallMethod: any, transaction: Transaction, setTransaction: any, btnMsg: string}) =>{

    const [customers, setCustomers] = useState<Customer[]>([]);
	const [games, setGames] = useState<Game[]>([]);

	const fetchSuggestionsCustomers = async(query: string) => {
		try{
			const response = await axios.get<Customer[]>(
				`${BACKEND_API_URL}/customers/autocomplete?query=${query}`
			);
			const data = await response.data;
			setCustomers(data);
		}catch(error){
			console.error("Error fetching suggestions:", error);
		}
	}

    const fetchSuggestionsGames = async(query: string) => {
		try{
			const response = await axios.get<Game[]>(
				`${BACKEND_API_URL}/games/autocomplete?query=${query}`
			);
			const data = await response.data;
			setGames(data);
		}catch(error){
			console.error("Error fetching suggestions:", error);
		}
	}
    
    const debouncedFetchSuggestionsCustomers = useCallback(debounce(fetchSuggestionsCustomers, 500), []);
	const debouncedFetchSuggestionsGames = useCallback(debounce(fetchSuggestionsGames, 500), []);

	useEffect(() => {
		return () => {
			debouncedFetchSuggestionsGames.cancel();
            debouncedFetchSuggestionsCustomers.cancel();
		};
	},[debouncedFetchSuggestionsGames, debouncedFetchSuggestionsCustomers]);

    const handleInputChangeCustomers = (event: any, value: any, reason: any) => {
		console.log("input", value, reason);

		if (reason === "input") {
			debouncedFetchSuggestionsCustomers(value);
		}
	};

	const handleInputChangeGames = (event: any, value: any, reason: any) => {
		console.log("input", value, reason);

		if (reason === "input") {
			debouncedFetchSuggestionsGames(value);
		}
	};

    return (
		<Container>
			<Card>
				<CardContent>
					<IconButton component={Link} to={`/transactions/`} sx={{float: "left"}}>
						<ArrowBackIcon/>
					</IconButton>
					<IconButton component={Link} sx={{ mr: 3 }} to={`/transactions/`}>
					</IconButton>{" "}
					<form onSubmit={apiCallMethod}>
                        <Autocomplete
							id="gameEntity"
							options={games}
							value={transaction.gameEntity}
							getOptionLabel={(option) => `${option.name} ${option.genre} ${option.price}`}
							renderInput={(params) => <TextField {...params} 
							label="Game" 
							variant="outlined" />}
							filterOptions={(x) => x}
                            sx={{mb: 2}}
							onInputChange={handleInputChangeGames}
							onChange={(event, value) => {
								if (value) {
									setTransaction({ ...transaction, gameEntity: value });
								}
							}}
						/>
                        <Autocomplete
                            id="customerEntity"
                            options={customers}
                            value={transaction.customerEntity}
                            getOptionLabel={(option) => `${option.firstname} ${option.lastname} ${option.email}`}
                            renderInput={(params) => <TextField {...params} 
							label="Customer" 
							variant="outlined" />}
                            filterOptions={(x) => x}
                            sx={{mb: 2}}
                            onInputChange={handleInputChangeCustomers}
                            onChange={(event, value) => {
                                if (value) {
                                    setTransaction({ ...transaction, customerEntity: value });
                                }
                            }}
						/>
                        <TextField
							id="format"
							label="Format"
							variant="outlined"
                            value={transaction.format}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setTransaction({ ...transaction, format: event.target.value})}
						/>
						<TextField
							id="quantity"
							label="Quantity"
							variant="outlined"
                            value={transaction.quantity}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setTransaction({ ...transaction, quantity: Number(event.target.value)})}
						/>
						
						<Button type="submit" sx={{float: "left"}}>{btnMsg}</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
}