import { CssBaseline, Container, Typography } from "@mui/material";
import React from "react";

export const Homepage = () => {
	return (
		<React.Fragment>
			<CssBaseline />

			<Container maxWidth="xl">
				<Typography variant="h1" component="h1" gutterBottom>
					Welcome to our game store. Use the navigation menu from above to buy your favourite games!
				</Typography>
			</Container>
		</React.Fragment>
	);
};