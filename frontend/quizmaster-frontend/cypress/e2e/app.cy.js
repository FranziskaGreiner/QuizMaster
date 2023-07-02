describe('Quiz App', () => {
  it('successfully loads and displays questions', () => {
    cy.visit('http://localhost:4200')

    cy.contains('Start quiz').click()

    // check if questions are loaded and displayed
    cy.get('mat-card').should('be.visible')
    // Wait up to 20 seconds for the 'mat-card-content' to be visible
    cy.get('mat-card-content', { timeout: 20000 }).should('be.visible')

    // check if each question has answers
    cy.get('mat-card-content').each(($card) => {
      cy.wrap($card).find('button').should('exist')
    })
  })
})
