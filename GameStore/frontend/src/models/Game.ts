import { Developer } from "./Developer";

export class Game{
    id?: number;
    name: string = "";
    genre: string = "";
    modes: string = "";
    yearOfRelease: number = 0;
    price: number = 0;
    developerEntity: Developer = new Developer();
}