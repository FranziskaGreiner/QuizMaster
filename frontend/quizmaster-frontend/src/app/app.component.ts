import { Component } from '@angular/core';
import {QuestionService} from "./question.service";
import {Question} from "./question";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'quizmaster-frontend';
  questions: Question[];

  constructor(private questionService: QuestionService) {
  }

  public getQuestions() {
    this.questionService.findAll().subscribe(questions => {
      console.log(questions);
      this.questions = questions;
    });
  }
}
