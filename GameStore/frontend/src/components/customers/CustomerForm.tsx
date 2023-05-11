import { Button, Card, CardActions, CardContent, IconButton, InputLabel, Select, TextField, MenuItem, Autocomplete} from "@mui/material";
import { Container } from "@mui/system";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { Customer } from "../../models/Customer";
import ArrowBackIcon from "@mui/icons-material/ArrowBack"
import axios from "axios";
import {debounce} from "lodash";
import { test } from "node:test";
import { Transaction } from "../../models/Transaction";

export const CustomerForm = (
    { apiCallMethod, customer, setCustomer, btnMsg} : 
    { apiCallMethod: any, customer: Customer, setCustomer: any, btnMsg: any}) =>{

	const [errors, setErrors] = useState({
		firstname: "",
		lastname: "",
        email: "",
        address: "",
		phoneNumber: "",
	})
    
	const validateForm = () => {
		let valid = true;
		
		const phonePattern = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;
		const emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

		const newErrors = {
			firstname: "",
            lastname: "",
            email: "",
            address: "",
            phoneNumber: "",
		}

		if(customer.firstname===""){
			newErrors.firstname="First name is required!";
			valid=false;
		}
		if(customer.lastname===""){
			newErrors.lastname="Last name is required";
			valid=false;
		}
		if(!emailPattern.test(customer.email)){
			newErrors.email="Email is invalid!";
			valid=false;
		}
		if(customer.address===""){
			newErrors.address="Address is required";
			valid=false;
		}
		if(!phonePattern.test(customer.phoneNumber)){
			newErrors.phoneNumber="Phone number is not valid";
			valid=false;
		}
	

		setErrors(newErrors);
		return valid;
	}

	const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
		event.preventDefault();
		if(validateForm()){
			apiCallMethod();
		}
	}

    return (
		<Container>
			<Card>
				<CardContent>
					<IconButton component={Link} to={`/customers/`} sx={{float: "left"}}>
						<ArrowBackIcon/>
					</IconButton>
					<IconButton component={Link} sx={{ mr: 3 }} to={`/customers/`}>
					</IconButton>{" "}
					<form onSubmit={handleSubmit}>
						<TextField
							id="firstname"
							label="First Name"
							variant="outlined"
                            value={customer.firstname}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setCustomer({ ...customer, firstname: event.target.value })}
							error={!!errors.firstname}
							helperText={errors.firstname}
						/>
						<TextField
							id="lastname"
							label="Last Name"
							variant="outlined"
                            value={customer.lastname}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setCustomer({ ...customer, lastname: event.target.value })}
							error={!!errors.lastname}
							helperText={errors.lastname}
						/>
                        <TextField
							id="email"
							label="Email"
							variant="outlined"
                            value={customer.email}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setCustomer({ ...customer, email: event.target.value })}
							error={!!errors.email}
							helperText={errors.email}
						/>
						<TextField
							id="address"
							label="Address"
							variant="outlined"
                            value={customer.address}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setCustomer({ ...customer, address: event.target.value })}
							error={!!errors.email}
							helperText={errors.email}
						/>
                        <TextField
							id="phoneNumber"
							label="Phone Number"
							variant="outlined"
                            value={customer.phoneNumber}
							fullWidth
							sx={{ mb: 2 }}
							onChange={(event) => setCustomer({ ...customer, phoneNumber: event.target.value })}
							error={!!errors.phoneNumber}
							helperText={errors.phoneNumber}
						/>
						<Button type="submit" sx={{float: "left"}}>{btnMsg}</Button>
					</form>
				</CardContent>
				<CardActions></CardActions>
			</Card>
		</Container>
	);
}