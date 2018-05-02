from pyfcm import FCMNotification

push_service = FCMNotification(api_key="AAAAaccXLck:APA91bHEthjjmJJ0vp8te617ztOkMNGVwTLBhzHAtihQ3NwTCMC1YlhmlzpC2T4VziFm7KgqhAOT0opcxQmKKgM_pSEjTfvRfIQvWQUAZHOo##############")

# OR initialize with proxies

# Your api-key can be gotten from:  https://console.firebase.google.com/project/<project-name>/settings/cloudmessaging

registration_id = "c4yFKTrfG9s:APA91bGdFDzVm_VPtgXDETEdUzGqIVeKPTrR2fOy8rY-4SxbYGWeVgxEss86LFjA-w43O4uuOtfCm4J6pxDaNdIV7QOh1S548Y5V0_jd7AqtMWo52ceaqthJHVmbcI-AkEu1476PTi6M"
message_title = "SNC"
message_body = "Notification Send By Python"
result = push_service.notify_single_device(registration_id=registration_id, message_title=message_title, message_body=message_body)

# Send to multiple devices by passing a list of ids.
#  registration_ids = ["<device registration_id 1>", "<device registration_id 2>", ...]
#message_title = "Uber update"
#message_body = "Hope you're having fun this weekend, don't forget to check today's news"
#result = push_service.notify_multiple_devices(registration_ids=registration_ids, message_title=message_title, message_body=message_body)

print result
