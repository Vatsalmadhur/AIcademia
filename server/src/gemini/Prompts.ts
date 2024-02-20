export const prompts = {
        intro: `
        Question: Give a brief Introduction of the <current_skill> and How similar it is to what I know (acquired_skills).
        - This will only be about the <current_skill> only.
        Answer: `,
        goals: `
        Question: Set some goals for me to complete today. I can dedicate 2 Hours today.
        ANSWER FORMAT:
          - GOAL heading
            - Time required
            - Description
        Answer:
        `,
        advice: `
        Question: Give me some advices on how to complete today's goals.
        Answer:
        `,
        resources: `
        Question: Provide me some resources which I can use to learn concepts whiich will help to complete today's goals. you can also provide urls for blogs, books, docs, and videos, etc for me to learn.
        Answer:
        `,
        quiz: `
        Question: Create a multiple-choice quiz focusing on the topics covered in today's learning goals. Ensure that each question is clear and concise, covering key concepts and skills addressed during today's session. Aim to include a total of 5 questions in the quiz. Provide varied difficulty levels to effectively assess comprehension across the topics covered. Additionally, strive to make each question informative, providing valuable insights regardless of the chosen answer.
          RESPONSE FORMAT:
            - Each question should have 4 options and the correct answer.
            - Return your response as an RFC8259-compliant JSON array using the structure
            - Respond in plaintext only, without any special formatting and don't wrap the response in a code block
            - Add %%% at the start and end of the array to indicate the start and end of the array
            - [{"question": "the question", "options": ["choice 1", "choice 2", "choice 3", "choice 4"],
"answer": "the correct choice index as number"}, {...}]
        Answer:
        `,
      };
