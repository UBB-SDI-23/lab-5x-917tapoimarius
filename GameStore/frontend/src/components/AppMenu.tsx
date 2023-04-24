import { Box, AppBar, Toolbar, IconButton, Typography, Button } from "@mui/material";
import { Link, useLocation } from "react-router-dom";
import SchoolIcon from "@mui/icons-material/School";
import LocalLibraryIcon from "@mui/icons-material/LocalLibrary";

export const AppMenu = () => {
	const location = useLocation();
	const path = location.pathname;

	return (
		<Box sx={{ marginBottom: "2%" }}>
			<AppBar position="absolute" >
				<Toolbar>
					<IconButton
						component={Link}
						to="/"
						size="large"
						edge="start"
						color="inherit"
						aria-label="school"
						sx={{ mr: 2 }}>
						<SchoolIcon />
					</IconButton>
					<Typography variant="h6" component="div" sx={{ mr: 5 }}>
						Game store
					</Typography>
					<Button
						variant={path.startsWith("/games") ? "outlined" : "text"}
						to="/games"
						component={Link}
						color="inherit"
						sx={{ mr: 5 }}
						startIcon={<LocalLibraryIcon />}>
						Games
					</Button>
					<Button
						variant={path.startsWith("/developers") ? "outlined" : "text"}
						to="/developers"
						component={Link}
						color="inherit"
						sx={{ mr: 5 }}
						startIcon={<LocalLibraryIcon />}>
						Developers
					</Button>
					<Button
						variant={path.startsWith("/customers") ? "outlined" : "text"}
						to="/customers"
						component={Link}
						color="inherit"
						sx={{ mr: 5 }}
						startIcon={<LocalLibraryIcon />}>
						Customers
					</Button>
					<Button
						variant={path.startsWith("/transactions") ? "outlined" : "text"}
						to="/transactions"
						component={Link}
						color="inherit"
						sx={{ mr: 5 }}
						startIcon={<LocalLibraryIcon />}>
						Transactions
					</Button>
				</Toolbar>
			</AppBar>
		</Box>
	);
};