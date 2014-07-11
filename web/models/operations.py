from web.orm import orm_base
from web.orm.mappings import ExpenseProjections, Users

import cherrypy

"""
Collection of DB operations for the web app.

Tutorial for using the session object in insert and update:

http://docs.sqlalchemy.org/en/rel_0_9/orm/session.html#adding-new-or-existing-items
"""

class UserOperations(object):
    
    @staticmethod
    def insert_user(username, password, can_read, can_write, can_exec):
        user = Users()
        user.username = username
        user.password = password
        user.can_read = can_read
        user.can_write = can_write
        user.can_exec = can_exec

        try:
            cherrypy.requests.db.add(user)
            cherrypy.requests.db.commit()
        except:
            cherrypy.request.db.rollback()
            raise

    @staticmethod
    def edit_user(userid, edit_fields):
        try:
            cherrypy.request.db.query(Users).filter(Users.userid == userid).limit(1) \
              .update(edit_fields)
            cherrypy.request.db.commit()
        except:
            cherrypy.request.db.rollback()
            raise

class ExpenseProjectionOperations(object):

    @staticmethod
    def insert_expense_projection(userid, expense_desc, expense_cost, \
      expense_expected_in, recurrence_factor=0):    
        projection = ExpenseProjections()
        projection.userid = userid
        projection.expense_desc = expense_desc
        projection.expense_cost = expense_cost
        projection.expense_expected_in = expense_expected_in
        projection.recurrence_factor = recurrence_factor
    
        try:
            cherrypy.request.db.add(projection)
            cherrypy.request.db.commit()
        except:
            cherrypy.request.db.rollback()
            raise
    
    @staticmethod
    def edit_expense_projection(projection_id, edit_fields):
        try:
            cherrypy.request.db.query(ExpenseProjections) \
              .filter(ExpenseProjections.projection_id == projection_id).limit(1).update(edit_fields)
            cherrypy.request.db.commit()
        except:
            cherrypy.request.db.rollback()
            raise
