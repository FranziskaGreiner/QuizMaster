import {Component} from '@angular/core';
import {QuestionService} from "./question.service";
import {Question} from "./question";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'quizmaster-frontend';
  questions: Question[] = [];
  selectedQuestionIndex: number | null = null;
  selectedAnswerIndex: number | null = null;

  constructor(private questionService: QuestionService) {
  }

  public getQuestions() {
    this.questionService.findAll().subscribe(questions => {
      this.questions = questions;
    });
  }

  handleAnswerSelection(questionIndex: number, answerIndex: number): void {
    this.selectedQuestionIndex = questionIndex;
    this.selectedAnswerIndex = answerIndex;
  }

  getAnswerClass(questionIndex: number, answerIndex: number): string {
    if (
      this.selectedQuestionIndex === questionIndex &&
      this.selectedAnswerIndex === answerIndex &&
      this.questions[questionIndex].answers[answerIndex].correct) {
      return 'correct';
    }
    else if (
      this.selectedQuestionIndex === questionIndex &&
      this.selectedAnswerIndex === answerIndex &&
      !this.questions[questionIndex].answers[answerIndex].correct
    ) {
      return 'incorrect';
    }
    return '';
  }
}
