import {Difficulty} from './difficulty.enum'
import {Answer} from "./answer";
import {Tag} from "./tag";

export class Question {
  id: number | undefined;
  question: string = '';
  description: string = '';
  answers: Answer[] = [];
  multipleCorrectAnswers: boolean | undefined;
  correctAnswers: string[] = [];
  correctAnswer: string = '';
  explanation: string = '';
  tip: string = '';
  tags: Tag[] = [];
  category: string = '';
  difficulty: Difficulty | undefined;
}
