import {Difficulty} from './difficulty.enum'
import {Answer} from "./answer";
import {Tag} from "./tag";

export class Question {
  id: number | undefined;
  question = '';
  description = '';
  answers: Answer[] = [];
  multipleCorrectAnswers: boolean | undefined;
  correctAnswers: string[] = [];
  correctAnswer= '';
  explanation = '';
  tip = '';
  tags: Tag[] = [];
  category = '';
  difficulty: Difficulty | undefined;
}
