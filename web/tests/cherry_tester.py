from StringIO import StringIO

import cherrypy
import urllib
import unittest

"""
Taken from https://bitbucket.org/Lawouach/cherrypy-recipes/src/50aff88dc4e2/testing/unit/serverless/?at=default
"""

class BaseCherryPyTestCase(unittest.TestCase):\
    def request(self, path="/", method="GET", app_path="", scheme="http",
      proto="HTTP/1.1", data=None, headers=None, **kwargs):
        """
        Notes from the original:

        - CherryPy is multithreaded. The response you will get from this method
          is a thread-data object attached to the current thread. Unless you use
          many threads from within a unit test, you can mostly forget about the
          thread data aspect of the response.

        - Responses are dispatched to a monted application's page handler, if
          found. This is the reason why you must indicate which app you are
          targetting with this request by specifying its mount point.
        """
        h = {"Host":"127.0.0.1"}

        if headers is not None:
            h.update(headers)

        if method in ("POST", "PUT") and not data:
            data = urllib.urlencode(kwargs)
            kwargs = None
            h["content-type"] = "application/x-www-form-urlencoded"

        # URL-encode kwargs
        query_string = urllib.urlencode(kwargs) if kwargs else None

        # request entity fields
        file_descriptor = None
        if data is not None:
            h["content-length"] = str(len(data))
            file_descriptor = StringIO(data)

        # Run the request against the app
        app = cherrypy.tree.apps.get(app_path)
        if not app:
            raise RuntimeError("Cherrypy app was not created properly or is terminated")

        app.release_serving()

        request, response = app.get_serving(local, remote, scheme, proto)

        try:
            h = [(key, val) for key, val in h.iteritems()]
            response = request.run(method, path, query_string, proto, h, file_descriptor)
        finally:
            if file_descriptor:
                file_descriptor.close()
                file_descriptor = None

        if response.output_status.startswith("500"):
            print response.body
            raise RuntimeError("Unexpected server error")

        response.collapse_body()
        return response
