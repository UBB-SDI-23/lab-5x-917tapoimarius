import { Button, Card, CardActions, CardContent, IconButton, InputLabel, Select, TextField, MenuItem, Autocomplete} from "@mui/material";
import { Container } from "@mui/system";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { Developer } from "../../models/Developer";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import {debounce} from "lodash";

export const DeveloperForm = (
    { apiCallMethod, developer, setDeveloper, btnMsg} : 
    { apiCallMethod: any, developer: Developer, setDeveloper: any, btnMsg: any}) =>{

	const [developers, setDevelopers] = useState<Developer[]>([]);

    const [selectedVal, setSelectedVal] = useState(0);

    return (
		<Container>
			<Card>
				<CardContent>
					<IconButton component={Link} to={`/developers`} sx={{float: "left"}}>
						<ArrowBackIcon/>
					</IconButton>
					<IconButton component={Link} sx={{ mr: 3 }} to={`/developers`}>
					</IconButton>{" "}
					<form onSubmit={apiCallMethod}>
						<TextField
							id="name"
							label="Name"
							variant="outlined"
                            value={developer.name}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDeveloper({ ...developer, name: event.target.value })}
						/>
						<TextField
							id="hq"
							label="HQ"
							variant="outlined"
                            value={developer.hq}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDeveloper({ ...developer, hq: event.target.value })}
						/>
						<TextField
							id="publisher"
							label="Publisher"
							variant="outlined"
                            value={developer.publisher}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDeveloper({ ...developer, publisher: event.target.value })}
						/>
						<TextField
							id="foundedIn"
							label="Founded In"
							variant="outlined"
                            value={developer.foundedIn}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDeveloper({ ...developer, foundedIn: Number(event.target.value) })}
						/>
                        <TextField
							id="revenue"
							label="Revenue"
							variant="outlined"
                            value={developer.revenue}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setDeveloper({ ...developer, revenue: Number(event.target.value) })}
						/>
                        <Button type="submit" sx={{float: "left"}}>{btnMsg}</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
}