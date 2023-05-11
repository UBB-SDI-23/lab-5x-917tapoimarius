import { Customer } from "./Customer";
import { Game } from "./Game";

export class Transaction{
    id?: number;
    gameEntity: Game = new Game();
    customerEntity: Customer = new Customer();
    format: string = "";
    quantity: number = 0;
    checkout: number = 0;
}