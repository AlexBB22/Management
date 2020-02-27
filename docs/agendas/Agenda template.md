## Example agenda

This is a template agenda. It gives an overview of what could be in your weekly agenda.
In the 'Points of action' part you will also find some topics to cover in/after your first meeting. 

---

Date:           24th Feb 2020

Main focus:     Making sure backbone is all correct

Chair:          Kanish

Note taker:     Kendra 


# Opening
*Here you check that everybody is present.*

# Approval of the agenda
*Make sure everything that needs to be discussed is in the agenda or add it if something is missing.*

# Points of action

 - Make sure you have a Mattermost channel (your TA will create one for you)
 General
 - Create a planning and put it on GitLab
	 - Implement all items on the todo list
 - Discuss a cake rule: If you are late or not there at all, you have to bring cake (or something similar)
 - Define a proper definition of done
 - Implement Scotts/Alexes database schema on server-side (ie. make repositories, entites, controllers for each table we have on the DB)
 - Implement the first feature: We can reserve a room at a certain date
    This has 4-5 main steps
        1. On login, we for now, give our role and name and surname. The server will send true/false if that user exists, if it does, it will also send its id 
        2. On GUI, select a certain date
        3. On button click, all rooms available on that date will be sent to the server
        4. The client can select one of these rooms
        5. On select, that room will be reserved with that clients id 
        
 - Things to research:
    - Gradle (see lecture 4)
        - What does it do?
        - How do you add a library to the build file?
    - Code structure (see lecture 4)
        - How to implement proper more complex database schemas. 
    - GUI
        - OpenFX
        - Making a super basic input button fields for that user to login/enter date/ select room for that selected date
        - Simply show a list of rooms 
    - API
        - Spring -> Make use of foreign keys, @ManyToMany, etc mapping relationships
        - Spring -> Do complex queries with JPQL
 

# Action points for next week (Scrum board)


# Any other business
*If anybody has something that should be discussed but came up with that after the agenda was finalized (in point 2), he/she should bring that up now so that it can be discussed after all.*

# Questions for the TA
1. Kendra - Should be put our Drive documents onto GitLab?
2. Kanish - I want to make sure if my method of connecting to the database is correct (i deleted two files)
3. Kanish - We don't have a mattermost channel, do we need one?
4. Scott/Alex - Database approach to bikes/way to do descriptions


# Question round
*If there are any questions, now is the time to ask them.*

# Closing
*Now you can start working on the project. Good luck!*
