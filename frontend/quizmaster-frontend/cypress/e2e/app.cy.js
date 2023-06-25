describe('Quiz App', () => {
  it('successfully loads and displays questions', () => {
    cy.visit('http://localhost:4200') // change to app's url

    cy.contains('Start quiz').click()

    // check if questions are loaded and displayed
    cy.get('mat-card').should('be.visible')
    cy.get('mat-card-content').should('be.visible')

    // check if each question has answers
    cy.get('mat-card-content').each(($card) => {
      cy.wrap($card).find('mat-checkbox').should('exist')
    })
  })
})
