from web.main import Pynance
from cherry_tester import BaseCherryPyTestCase

import cherrypy
import unittest

"""
Equivalent to
https://bitbucket.org/Lawouach/cherrypy-recipes/src/50aff88dc4e24206518ec32e1c32af043f2729da/testing/unit/serverless/test.py?at=default
"""

"""
def setUpModule():
    cherrypy.tree.mount(Pynance(), "/")
    cherrypy.engine.start()
setup_module = setUpModule

def tearDownModule():
    cherrypy.engine.exit()
teardown_module = tearDownModule
"""

class ShallowTests(BaseCherryPyTestCase):
    
    def setUp(self):
        cherrypy.tree.mount(Pynance(), "/")
        cherrypy.engine.start()

    def tearDown(self):
        cherrypy.engine.exit()
    
    def test_index(self):
        response = self.request("/")
        self.assertEqual(response.output_status, "200 OK")

if __name__ == "__main__":
    unittest.main()
