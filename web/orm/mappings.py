from orm_base import TableTemplate
from sqlalchemy import BOOLEAN, Column, DECIMAL, ForeignKey, INTEGER, TIMESTAMP, VARCHAR
from sqlalchemy.ext.declarative import declarative_base

import datetime

BUDGET_PRECISION = 8
BUDGET_SCALE = 2

Base = declarative_base()

class Users(Base, TableTemplate):
    """
    The users of this web app. All userids in other tables map to this table.
    """
    __tablename__ = "users"

    userid = Column(INTEGER, primary_key = True)
    username = Column(VARCHAR(length = 255))
    password = Column(VARCHAR(length = 255))
    total_budget = Column(DECIMAL(precision = BUDGET_PRECISION, scale = BUDGET_SCALE))
    can_read = Column(BOOLEAN, default = True)
    can_write = Column(BOOLEAN, default =  False)
    can_exec = Column(BOOLEAN, default = False)

class Expenses(TableTemplate, Base):
    """
    A listing of all the expenses a user has made. This table allows the distinction
    between planned expenses and unplanned expenses.
    """
    __tablename__ = "expenses"

    expense_id = Column(INTEGER, primary_key = True)
    userid = Column(INTEGER, ForeignKey("users.userid"))
    expense_desc = Column(VARCHAR(length = 255))
    expense_cost = Column(DECIMAL(precision = BUDGET_PRECISION, scale = BUDGET_SCALE))
    is_planned = Column(BOOLEAN, default = False)
    expense_date = Column(TIMESTAMP, default = datetime.datetime.utcnow)

class ExpenseBreakdown(TableTemplate, Base):
    """
    The subcategories of an expense. For instance, "Vacation" may include travel
    costs, lodging, food, etc.
    """
    __tablename__ = "expense_breakdown"

    breakdown_id = Column(INTEGER, primary_key = True)
    expenseid = Column(INTEGER, ForeignKey("expenses.expense_id"))
    expense_desc = Column(VARCHAR(length = 255))
    expense_cost = Column(DECIMAL(precision = BUDGET_PRECISION, scale = BUDGET_SCALE))

class ExpenseProjections(TableTemplate, Base):
    """
    A table of projected expenses. recurrence_factor is a multiplier on how
    many times is this particular projection expected to recur. Since the period
    over which we budget is arbitrary (i.e., we can budget for a month, a quarter,
    etc.---entirely up to us).
    """
    __tablename__ = "expense_projections"

    projection_id = Column(INTEGER, primary_key = True)
    userid = Column(INTEGER, ForeignKey("users.userid"))
    expense_desc = Column(VARCHAR(length = 255))
    expense_cost = Column(DECIMAL(precision = BUDGET_PRECISION, scale = BUDGET_SCALE))
    expense_expected_in = Column(INTEGER)
    recurrence_factor = Column(INTEGER)
    expense_fulfilled = Column(INTEGER, ForeignKey("expenses.expense_id"))

class History(TableTemplate, Base):
    """
    Table of budget projection summaries.
    """
    __tablename__ = "history"

    history_id = Column(INTEGER, primary_key = True)
    userid = Column(INTEGER, ForeignKey("users.userid"))
    snapshot_date = Column(TIMESTAMP, default = datetime.datetime.utcnow)
    current_budget = Column(DECIMAL(precision = BUDGET_PRECISION, scale = BUDGET_SCALE))
    period_expenses = Column(DECIMAL(precision = BUDGET_PRECISION, scale = BUDGET_SCALE))
