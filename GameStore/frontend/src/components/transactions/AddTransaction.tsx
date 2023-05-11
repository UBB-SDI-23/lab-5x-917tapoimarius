import { useEffect, useState } from "react";
import { BACKEND_API_URL } from "../../constants";
import { Game } from "../../models/Game";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { TransactionForm } from "./TransactionFrom";
import { Customer } from "../../models/Customer";
import { Transaction } from "../../models/Transaction";

export const AddTransaction = () => {
	const navigate = useNavigate();

	const [transaction, setTransaction] = useState<Transaction>(
        new Transaction()
    );

	const addTransaction = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			console.log(transaction);
			await axios.post(`${BACKEND_API_URL}/transactions/`, transaction);
			navigate("/transactions/");
		} catch (error) {
			console.log(error);
		}
	};

	return(
		<TransactionForm
			apiCallMethod={addTransaction}
			transaction={transaction}
			setTransaction={setTransaction}
			btnMsg="Add transaction"
		/>
	)
};