import {Difficulty} from "./src/app/difficulty.enum";
import {Question} from "./src/app/question";

export const mockQuestions: Question[] = [
  {
    id: 1,
    question: 'Question 1',
    description: 'Description 1',
    answers: [
      { id: 1, answer: 'Answer 1', correct: true },
    ],
    multipleCorrectAnswers: false,
    correctAnswers: ['Answer 1'],
    correctAnswer: 'Answer 1',
    explanation: 'Explanation 1',
    tip: 'Tip 1',
    tags: [
      { id: 1, name: 'Tag 1', questions: [] },
    ],
    category: 'Category 1',
    difficulty: Difficulty.EASY
  },
  {
    id: 2,
    question: 'Question 2',
    description: 'Description 2',
    answers: [
      { id: 2, answer: 'Answer 2', correct: true },
    ],
    multipleCorrectAnswers: false,
    correctAnswers: ['Answer 2'],
    correctAnswer: 'Answer 2',
    explanation: 'Explanation 2',
    tip: 'Tip 2',
    tags: [
      { id: 2, name: 'Tag 2', questions: [] },
    ],
    category: 'Category 2',
    difficulty: Difficulty.MEDIUM
  }
];
