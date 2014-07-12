from web.orm import orm_base
from web.orm.mappings import ExpenseProjections, Users

import cherrypy

"""
Collection of DB operations for the web app.

Tutorial for using the session object in insert and update:

http://docs.sqlalchemy.org/en/rel_0_9/orm/session.html#adding-new-or-existing-items
"""

class DBOperations(object):
    """
    An instance of DBOperations allows you to perform insertions and updates on
    a single table in the database. Construct it with a mapping instance
    (instances of SQLAlchemy Base or, in this project, TableTemplate), and
    use that data to perform the insertion/update.
    """
    
    def __init__(self, ormmap):
        self.ormmap = ormmap

    def insert(self):
        """
        Insert a row into a particular table in the database. The details of the
        transaction (i.e., data, which table, etc.) depends on the mapped object
        instance used to construct this DBOperations object.
        """
        try:
            cherrypy.requests.db.add(self.ormmap)
            cherrypy.requests.db.commit()
        except:
            cherrypy.request.db.rollback()
            raise

    def edit(self, filter_fn, edit_fields, limit=None):
        """
        Edit a record in a specific table in the database. The table is derived
        from the type of the mapped object used to construct this DBOperations
        object while the record to be edited is described by filter_fn. The
        fields to be updated as well as their updated values are described by
        the map edit_fields.

        filter_fn - A function returning a boolean value representing the WHERE
            clause of the UPDATE query. The function should take no arguments.
        edit_fields - A map/dictionary of the fields to be edited. Each map
            entry is as follows "table_field_name" : "new value".
        """
        try:
            if limit:
                cherrypy.requests.db.query(type(ormmap)).filter(filter_fn()) \
                  .update(edit_fields)
            else:
                cherrypy.requests.db.query(type(ormmap)).filter(filter_fn()).limit(1) \
                  .update(edit_fields)
            cherrypy.requests.db.commit()
        except:
            cherrypy.requests.db.rollback()
            raise

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
