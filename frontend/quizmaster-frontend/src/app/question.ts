import {Difficulty} from './difficulty.enum'

export class Question {
  id: string;
  question: string;
  description: string;
  answers: string[];
  multipleCorrectAnswers: boolean;
  correctAnswers: string[];
  explanation: string;
  tip: string;
  tags: string[];
  category: string;
  difficulty: Difficulty;
}
