import { useEffect, useState } from "react";
import { BACKEND_API_URL } from "../../constants";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { CustomerForm } from "./CustomerForm";
import { Customer } from "../../models/Customer";

export const AddCustomer = () => {
	const navigate = useNavigate();

	const [customer, setCustomer] = useState<Customer>(
        new Customer()
    );

	

	const addCustomer = async () => {
		try {
			await axios.post(`${BACKEND_API_URL}/customers/`, customer);
			navigate("/customers/");
		} catch (error) {
			console.log(error);
		}
	};

	return(
		<CustomerForm
			apiCallMethod={addCustomer}
			customer={customer}
			setCustomer={setCustomer}
			btnMsg="Add customer"
		/>
	)
};