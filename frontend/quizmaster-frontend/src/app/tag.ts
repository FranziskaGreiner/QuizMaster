import {Question} from "./question";

export class Tag {
  id: number | undefined;
  name: string = '';
  questions: Question[] = [];
}
