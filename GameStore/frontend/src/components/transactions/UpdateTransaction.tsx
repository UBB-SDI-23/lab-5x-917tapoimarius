import { useNavigate, useParams } from "react-router-dom"
import { TransactionForm } from "./TransactionFrom";
import { useEffect, useState } from "react";
import { Game } from "../../models/Game";
import { BACKEND_API_URL } from "../../constants";
import axios from "axios";
import { Transaction } from "../../models/Transaction";

export const UpdateTransaction = () => {
    const navigate = useNavigate();
    const {id} = useParams();

    useEffect(() => {
        fetch(`${BACKEND_API_URL}/transactions/${id}`)
            .then(res => res.json())
            .then(data => {
                setTransaction(data);
            })
    }, []);;

    const [transaction, setTransaction] = useState<Transaction>(
        new Transaction()
    );

	const updateTransaction = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			await axios.put(`${BACKEND_API_URL}/transactions/${id}`, transaction);
			navigate("/transactions/");
		} catch (error) {
			console.log(error);
		}
	};

    return(
        <TransactionForm
            apiCallMethod={updateTransaction}
            transaction={transaction}
            setTransaction={setTransaction}
            btnMsg="Update transaction"
        />
    )
}