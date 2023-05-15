import { Component } from '@angular/core';
import {QuestionService} from "./question.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'quizmaster-frontend';

  constructor(public questionService: QuestionService) {
  }

  public getQuestions() {
    this.questionService.findAll();
  }
}
